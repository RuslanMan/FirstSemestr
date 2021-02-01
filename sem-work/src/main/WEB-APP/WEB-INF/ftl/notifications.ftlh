<#include "base.ftlh">
<@html "Уведомления">
    <h1>Уведомления</h1>
    <hr>
    <#list notifications as not>
        <h2>${not.title}</h2>
        <p>${not.date}</p>
        <h3>${not.text}</h3>
        <hr>
    </#list>
</@>