<#include "base.ftlh">
<@html "Сообщения">
    <div style="max-width: 1000px">
        <div style="width: 1000px">
            <#list messages as message>
                <#if message.operator??>
                    <div style="margin-right: fill; max-width: 600px">
                        <p>${message.operator.login}</p>
                        <p>${message.timestamp}</p>
                        <p>${message.text}</p>
                    </div>
                <#else>
                    <div style="margin-left: fill; max-width: 600px">
                        <p>${message.timestamp}</p>
                        <p>${message.text}</p>
                    </div>
                </#if>
            </#list>
        </div>
        <#if wait = false>
            <form method="post" action="/message" style="margin-left: fill">
                <input type="text" name="text" placeholder="Вопрос" style="display: block">
                <input type="submit" placeholder="Отправить" style="display: block">
            </form>
        </#if>
    </div>
</@>