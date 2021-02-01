<#ftl encoding='UTF-8'>

<#macro html title>

    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
    </head>
    <body>
    <div style="margin-bottom: 100px">
        <span style="margin-left: 70px"><a href="/home">HOME</a></span>
        <span style="margin-left: 70px"><a href="/news">NEWS</a></span>
        <#if user??>
            <span style="margin-left: 70px"><a href="/message">MESSAGE</a></span>
            <span style="margin-left: 70px"><a href="/profile">PROFILE</a></span>
            <span style="margin-left: 70px"><a href="/logout">LOGOUT</a></span>
        <#else>
            <span style="margin-left: 70px"><a href="/login">LOGIN</a></span>
            <span style="margin-left: 70px"><a href="/register">REGISTER</a></span>
        </#if>
    </div>
    <#nested>
    </body>
    </html>

</#macro>