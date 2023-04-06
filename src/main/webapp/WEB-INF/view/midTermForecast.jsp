<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>${region} 중기 예보</title>
</head>
<body>
<p>시간: ${tmFc}</p>
<c:choose>
    <c:when test="${type == 'FORECAST'}">
        <p>${result.wfSv}</p>
    </c:when>
    <c:when test="${type == 'LAND'}">
        <table>
            <tr>
                <td>날짜</td>
                <td>강수 확률</td>
                <td>날씨 예보</td>
            </tr>
            <tr>
                <td>3일 후 오전</td>
                <td>${result.value.rnSt3Am}</td>
                <td>${result.value.wf3Am}</td>
            </tr>
            <tr>
                <td>3일 후 오후</td>
                <td>${result.value.rnSt3Pm}</td>
                <td>${result.value.wf3Pm}</td>
            </tr>
            <tr>
                <td>4일 후 오전</td>
                <td>${result.value.rnSt4Am}</td>
                <td>${result.value.wf4Am}</td>
            </tr>
            <tr>
                <td>4일 후 오후</td>
                <td>${result.value.rnSt4Pm}</td>
                <td>${result.value.wf4Pm}</td>
            </tr>
            <tr>
                <td>5일 후 오전</td>
                <td>${result.value.rnSt5Am}</td>
                <td>${result.value.wf5Am}</td>
            </tr>
            <tr>
                <td>5일 후 오후</td>
                <td>${result.value.rnSt5Pm}</td>
                <td>${result.value.wf5Pm}</td>
            </tr>
            <tr>
                <td>6일 후 오전</td>
                <td>${result.value.rnSt6Am}</td>
                <td>${result.value.wf6Am}</td>
            </tr>
            <tr>
                <td>6일 후 오후</td>
                <td>${result.value.rnSt6Pm}</td>
                <td>${result.value.wf6Pm}</td>
            </tr>
            <tr>
                <td>7일 후 오전</td>
                <td>${result.value.rnSt7Am}</td>
                <td>${result.value.wf7Am}</td>
            </tr>
            <tr>
                <td>7일 후 오후</td>
                <td>${result.value.rnSt7Pm}</td>
                <td>${result.value.wf7Pm}</td>
            </tr>
            <tr>
                <td>8일 후</td>
                <td>${result.value.rnSt8}</td>
                <td>${result.value.wf8}</td>
            </tr>
            <tr>
                <td>9일 후</td>
                <td>${result.value.rnSt9}</td>
                <td>${result.value.wf9}</td>
            </tr>
            <tr>
                <td>10일 후</td>
                <td>${result.value.rnSt10}</td>
                <td>${result.value.wf10}</td>
            </tr>
        </table>
    </c:when>
    <c:when test="${type == 'SEA'}">
        <table>
            <tr>
                <td>날짜</td>
                <td>날씨예보</td>
                <td>최저 예상파고</td>
                <td>최고 예상파고</td>
            </tr>
            <tr>
                <td>3일 후 오전</td>
                <td>${result.value.wf3Am}</td>
                <td>${result.value.wh3AAm}</td>
                <td>${result.value.wh3BAm}</td>
            </tr>
            <tr>
                <td>3일 후 오후</td>
                <td>${result.value.wf3Pm}</td>
                <td>${result.value.wh3APm}</td>
                <td>${result.value.wh3BPm}</td>
            </tr>
            <tr>
                <td>4일 후 오전</td>
                <td>${result.value.wf4Am}</td>
                <td>${result.value.wh4AAm}</td>
                <td>${result.value.wh4BAm}</td>
            </tr>
            <tr>
                <td>4일 후 오후</td>
                <td>${result.value.wf4Pm}</td>
                <td>${result.value.wh4APm}</td>
                <td>${result.value.wh4BPm}</td>
            </tr>
            <tr>
                <td>5일 후 오전</td>
                <td>${result.value.wf5Am}</td>
                <td>${result.value.wh5AAm}</td>
                <td>${result.value.wh5BAm}</td>
            </tr>
            <tr>
                <td>5일 후 오후</td>
                <td>${result.value.wf5Pm}</td>
                <td>${result.value.wh5APm}</td>
                <td>${result.value.wh5BPm}</td>
            </tr>
            <tr>
                <td>6일 후 오전</td>
                <td>${result.value.wf6Am}</td>
                <td>${result.value.wh6AAm}</td>
                <td>${result.value.wh6BAm}</td>
            </tr>
            <tr>
                <td>6일 후 오후</td>
                <td>${result.value.wf6Pm}</td>
                <td>${result.value.wh6APm}</td>
                <td>${result.value.wh6BPm}</td>
            </tr>
            <tr>
                <td>7일 후 오전</td>
                <td>${result.value.wf7Am}</td>
                <td>${result.value.wh7AAm}</td>
                <td>${result.value.wh7BAm}</td>
            </tr>
            <tr>
                <td>7일 후 오후</td>
                <td>${result.value.wf7Pm}</td>
                <td>${result.value.wh7APm}</td>
                <td>${result.value.wh7BPm}</td>
            </tr>
            <tr>
                <td>8일 후</td>
                <td>${result.value.wf8}</td>
                <td>${result.value.wh8A}</td>
                <td>${result.value.wh8B}</td>
            </tr>
            <tr>
                <td>9일 후</td>
                <td>${result.value.wf9}</td>
                <td>${result.value.wh9A}</td>
                <td>${result.value.wh9B}</td>
            </tr>
            <tr>
                <td>10일 후</td>
                <td>${result.value.wf10}</td>
                <td>${result.value.wh10A}</td>
                <td>${result.value.wh10B}</td>
            </tr>
        </table>
    </c:when>
    <c:when test="${type == 'TEMPERATURE'}">
        <table>
            <tr>
                <td>날짜</td>
                <td>예상최저기온</td>
                <td>예상최저기온 하한 범위</td>
                <td>예상최저기온 상한 범위</td>
                <td>예상최고기온</td>
                <td>예상최고기온 하한 범위</td>
                <td>예상최고기온 상한 범위</td>
            </tr>
            <tr>
                <td>3일 후</td>
                <td>${result.value.taMin3}</td>
                <td>${result.value.taMin3Low}</td>
                <td>${result.value.taMin3High}</td>
                <td>${result.value.taMax3}</td>
                <td>${result.value.taMax3Low}</td>
                <td>${result.value.taMax3High}</td>
            </tr>
            <tr>
                <td>4일 후</td>
                <td>${result.value.taMin4}</td>
                <td>${result.value.taMin4Low}</td>
                <td>${result.value.taMin4High}</td>
                <td>${result.value.taMax4}</td>
                <td>${result.value.taMax4Low}</td>
                <td>${result.value.taMax4High}</td>
            </tr>
            <tr>
                <td>5일 후</td>
                <td>${result.value.taMin5}</td>
                <td>${result.value.taMin5Low}</td>
                <td>${result.value.taMin5High}</td>
                <td>${result.value.taMax5}</td>
                <td>${result.value.taMax5Low}</td>
                <td>${result.value.taMax5High}</td>
            </tr>
            <tr>
                <td>6일 후</td>
                <td>${result.value.taMin6}</td>
                <td>${result.value.taMin6Low}</td>
                <td>${result.value.taMin6High}</td>
                <td>${result.value.taMax6}</td>
                <td>${result.value.taMax6Low}</td>
                <td>${result.value.taMax6High}</td>
            </tr>
            <tr>
                <td>7일 후</td>
                <td>${result.value.taMin7}</td>
                <td>${result.value.taMin7Low}</td>
                <td>${result.value.taMin7High}</td>
                <td>${result.value.taMax7}</td>
                <td>${result.value.taMax7Low}</td>
                <td>${result.value.taMax7High}</td>
            </tr>
            <tr>
                <td>8일 후</td>
                <td>${result.value.taMin8}</td>
                <td>${result.value.taMin8Low}</td>
                <td>${result.value.taMin8High}</td>
                <td>${result.value.taMax8}</td>
                <td>${result.value.taMax8Low}</td>
                <td>${result.value.taMax8High}</td>
            </tr>
            <tr>
                <td>9일 후</td>
                <td>${result.value.taMin9}</td>
                <td>${result.value.taMin9Low}</td>
                <td>${result.value.taMin9High}</td>
                <td>${result.value.taMax9}</td>
                <td>${result.value.taMax9Low}</td>
                <td>${result.value.taMax9High}</td>
            </tr>
            <tr>
                <td>10일 후</td>
                <td>${result.value.taMin10}</td>
                <td>${result.value.taMin10Low}</td>
                <td>${result.value.taMin10High}</td>
                <td>${result.value.taMax10}</td>
                <td>${result.value.taMax10Low}</td>
                <td>${result.value.taMax10High}</td>
            </tr>
        </table>
    </c:when>
</c:choose>

</body>
</html>