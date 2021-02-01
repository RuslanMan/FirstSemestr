<#include "base.ftlh">
<@html "Сообщения">
    <div style="max-width: 1000px">
        <#list messages as message>
            <p>${message.user.login}</p>
            <p>${message.timestamp}</p>
            <p>${message.text}</p>
            <form method="post" action="/message">
                <input type="text" name="text" placeholder="Ответ" style="display: block">
                <input type="hidden" name="user" value="${message.user.login}" style="display: block">
                <input type="submit" placeholder="Отправить" style="display: block">
            </form>
        </#list>
    </div>
</@>