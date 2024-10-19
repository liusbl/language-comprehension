fun main() {
    val command = """
        curl "https://ekalba.lt/action/vocabulary/records/public" ^
  -H "Accept: application/json, text/plain, */*" ^
  -H "Accept-Language: en-LT,en;q=0.9,es-LT;q=0.8,es;q=0.7,en-GB;q=0.6,en-US;q=0.5,lt;q=0.4" ^
  -H "Connection: keep-alive" ^
  -H "Content-Type: application/json;charset=UTF-8" ^
  -H "Cookie: XSRF-TOKEN=1f7ad8e3-82d0-4570-a07a-78429115706e; SESSION=NGYyNmYwMjItNTc0My00MzNiLTg0Y2UtMTIxY2RlZDJmNWI1" ^
  -H "Origin: https://ekalba.lt" ^
  -H "Referer: https://ekalba.lt/bendrines-lietuviu-kalbos-zodynas/" ^
  -H "Sec-Fetch-Dest: empty" ^
  -H "Sec-Fetch-Mode: cors" ^
  -H "Sec-Fetch-Site: same-origin" ^
  -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36" ^
  -H "X-XSRF-TOKEN: 1f7ad8e3-82d0-4570-a07a-78429115706e" ^
  -H "locale: lt" ^
  -H ^"sec-ch-ua: ^\^"Google Chrome^\^";v=^\^"129^\^", ^\^"Not=A?Brand^\^";v=^\^"8^\^", ^\^"Chromium^\^";v=^\^"129^\^"^" ^
  -H "sec-ch-ua-mobile: ?0" ^
  -H ^"sec-ch-ua-platform: ^\^"Windows^\^"^" ^
  --data-raw ^"^{^\^"page^\^":1,^\^"pageSize^\^":5000,^\^"searchFieldMap^\^":^{^},^\^"vocabularyUuid^\^":^\^"0a6409e6-701f-18ab-8170-20311dec0055^\^",^\^"viewTypeEnum^\^":1,^\^"sortingOrderList^\^":^[^{^\^"fieldName^\^":^\^"map.sortHeader.sort^\^",^\^"sortingOrder^\^":^\^"asc^\^"^},^{^\^"fieldName^\^":^\^"map.header.sort^\^",^\^"sortingOrder^\^":^\^"asc^\^"^},^{^\^"fieldName^\^":^\^"map.headerHomonym.sort^\^",^\^"sortingOrder^\^":^\^"asc^\^",^\^"nullSorting^\^":^\^"first^\^"^}^],^\^"fieldMap^\^":^{^\^"firstLetterSearch^\^":^{^\^"fieldNames^\^":^[^\^"firstLetterSearch^\^"^],^\^"values^\^":^[^\^"C*^\^"^]^}^},^\^"trackStatusEnumlist^\^":null,^\^"resourceStatusEnumList^\^":^[^\^"ACTIVE^\^",^\^"BLOCKED^\^"^]^}^"
    """.trimIndent()

    val process = Runtime.getRuntime().exec("cmd.exe $command")
    val result = process.inputStream.bufferedReader().readText()

    println(result)
}
