<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>단기/중기 예보</title>
</head>
<body>
<div>
    <p>단기 예보</p>
    <fieldset>
        <input type="radio" id="ultra-short-nowcast" name="village-forecast-type" value="ultraShortNowcast" checked>
        <label for="ultra-short-nowcast">초단기실황</label>
        <input type="radio" id="ultra-short-forecast" name="village-forecast-type" value="ultraShortForecast">
        <label for="ultra-short-forecast">초단기예보</label>
        <input type="radio" id="village-forecast" name="village-forecast-type" value="villageForecast">
        <label for="village-forecast">단기예보</label>
        <br>
        <select name="region-first-level" id="region-first-level-select" onchange="onFirstLevelChange()">
            <option value="">시/도</option>
            <c:forEach var="firstLevel" items="${firstLevels}">
                <option value="${firstLevel}">${firstLevel}</option>
            </c:forEach>
        </select>
        <select name="region-second-level" id="region-second-level-select" onchange="onSecondLevelChange()">
            <option value="">시/군/구</option>
        </select>
        <select name="region-third-level" id="region-third-level-select">
            <option value="">읍/면/동</option>
        </select>
        <br>
        <button type="button" onclick="fetchVillageForecast()">조회</button>
    </fieldset>
    <div id="village-forecast-result"></div>
</div>
<div>
    <p>중기 예보</p>
    <fieldset>
        <input type="radio" id="mid-term-forecast" name="mid-term-forecast-type" value="forecast" checked>
        <label for="mid-term-forecast">중기전망</label>
        <input type="radio" id="mid-term-land-forecast" name="mid-term-forecast-type" value="land">
        <label for="mid-term-land-forecast">중기육상예보</label>
        <input type="radio" id="mid-term-temperature" name="mid-term-forecast-type" value="temperature">
        <label for="mid-term-temperature">중기기온</label>
        <input type="radio" id="mid-term-sea-forecast" name="mid-term-forecast-type" value="sea">
        <label for="mid-term-sea-forecast">중기해상예보</label>
        <br>
        <select name="mid-term-region" id="mid-term-region-select">
            <option value="">구역</option>
            <c:forEach var="midTermRegion" items="${midTermRegions}">
                <option value="${midTermRegion}">${midTermRegion}</option>
            </c:forEach>
        </select>
        <br>
        <button type="button" onclick="gotoMidTermForecast()">조회</button>
    </fieldset>
</div>
<script>
    const categoryNames = {
        POP: '강수확률(%)',
        PTY: '강수형태',
        PCP: '1시간 강수량(mm)',
        REH: '습도(%)',
        SNO: '1시간 신적설(cm)',
        SKY: '하늘상태',
        TMP: '1시간 기온(℃)',
        TMN: '일 최저기온(℃)',
        TMX: '일 최고기온(℃)',
        UUU: '풍속(동서성분)(m/s)',
        VVV: '풍속(남북성분)(m/s)',
        WAV: '파고(M)',
        VEC: '풍향(deg)',
        WSD: '풍속(m/s)',
        T1H: '기온(℃)',
        RN1: '1시간 강수량(mm)',
        LGT: '낙뢰(kA)'
    };

    function clearSelect(id, text) {
        const element = document.getElementById(id);
        element.innerHTML = '';
        const option = document.createElement('option');
        option.value = '';
        option.innerHTML = text;
        element.appendChild(option);
    }

    function onFirstLevelChange() {
        clearSelect('region-second-level-select', '시/군/구');
        clearSelect('region-third-level-select', '읍/면/동');
        const firstLevelSelect = document.getElementById('region-first-level-select');
        if (firstLevelSelect.value !== '') {
            fetch('/api/region/getSecondLevels?firstLevel=' + firstLevelSelect.value).then(response => response.json()).then(levels => {
                const secondLevelSelect = document.getElementById('region-second-level-select');
                for (const level of levels) {
                    const option = document.createElement('option');
                    option.value = level;
                    option.innerHTML = level;
                    secondLevelSelect.appendChild(option);
                }
            });
        }
    }

    function onSecondLevelChange() {
        clearSelect('region-third-level-select', '읍/면/동');
        const firstLevelSelect = document.getElementById('region-first-level-select');
        const secondLevelSelect = document.getElementById('region-second-level-select');
        if (secondLevelSelect.value !== '') {
            fetch('/api/region/getThirdLevels?firstLevel=' + firstLevelSelect.value + '&secondLevel=' + secondLevelSelect.value)
                .then(response => response.json())
                .then(levels => {
                    const thirdLevelSelect = document.getElementById('region-third-level-select');
                    for (const level of levels) {
                        const option = document.createElement('option');
                        option.value = level;
                        option.innerHTML = level;
                        thirdLevelSelect.appendChild(option);
                    }
                });
        }
    }

    function fetchVillageForecast() {
        const resultElement = document.getElementById('village-forecast-result');
        const forecastType = document.querySelector('input[name="village-forecast-type"]:checked').value;
        const firstLevel = document.getElementById('region-first-level-select').value;
        const secondLevel = document.getElementById('region-second-level-select').value;
        const thirdLevel = document.getElementById('region-third-level-select').value;
        if (firstLevel !== '') {
            fetch('/api/villageForecast/' + forecastType + '?firstLevel=' + firstLevel + (secondLevel !== '' ? '&secondLevel=' + secondLevel : '') + (thirdLevel !== '' ? '&thirdLevel=' + thirdLevel : ''))
                .then(response => response.json())
                .then(response => {
                    resultElement.innerHTML = '';
                    if (Array.isArray(response) && response.length > 0) {
                        if (forecastType === 'ultraShortNowcast') {
                            resultElement.innerHTML = '<p>날짜: ' + response[0].baseDate + '<p><br><p>시간: ' + response[0].baseTime + '</p><br><table><tr><th>항목</th><th>값</th></tr>' + response.map(x => '<tr><td>' + categoryNames[x.category] + '</td><td>' + x.obsrValue + '</td></tr>').join('') + '</table>';
                        } else {
                            response = response.reduce((acc, cur) => {
                                const key = cur.fcstDate + ':' + cur.fcstTime;
                                if (!(key in acc)) {
                                    acc[key] = [];
                                }
                                acc[key].push(cur);
                                return acc;
                            }, {});
                            const keys = Object.keys(response).sort();
                            const selectTime = document.createElement('select');
                            for (const key of keys) {
                                const option = document.createElement('option');
                                option.value = key;
                                option.innerHTML = key;
                                selectTime.appendChild(option);
                            }
                            const tableDisplay = document.createElement('div');
                            function displayResult(key) {
                                tableDisplay.innerHTML = '<table><tr><th>항목</th><th>값</th></tr>' + response[key].map(x => '<tr><td>' + categoryNames[x.category] + '</td><td>' + x.fcstValue + '</td></tr>').join('') + '</table>';
                            }
                            selectTime.onchange = function () {
                                displayResult(selectTime.value);
                            };
                            resultElement.appendChild(selectTime);
                            resultElement.appendChild(tableDisplay);
                            displayResult(selectTime.value);
                        }
                    } else {
                        resultElement.innerHTML = '단기 예보 조회에 실패했습니다';
                    }
                });
        }
    }

    document.querySelectorAll('input[name="mid-term-forecast-type"]').forEach(node => {
        node.onchange = function () {
            fetch('/api/region/getMidTermRegions?type=' + node.value)
                .then(response => response.json())
                .then(regions => {
                    clearSelect('mid-term-region-select', '구역');
                    const element = document.getElementById('mid-term-region-select');
                    for (const region of regions) {
                        const option = document.createElement('option');
                        option.value = region;
                        option.innerHTML = region;
                        element.appendChild(option);
                    }
                });
        };
    });

    function gotoMidTermForecast() {
        const type = document.querySelector('input[name="mid-term-forecast-type"]:checked').value;
        const region = document.getElementById('mid-term-region-select').value;
        if (region !== '') {
            window.location.href = '/midTermForecast?type=' + type + '&region=' + region;
        }
    }
</script>
</body>
</html>