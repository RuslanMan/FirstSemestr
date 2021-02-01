<#include "base.ftlh">
<@html "Профиль">
    <h1>Профиль</h1>
    <div style="max-width: 1000px">
        <div style="float: left">
            <img src="${user.image!"https://sun9-22.userapi.com/63MMjkNDNsM6oWPXaE52onYExU8CkdG5k0idRQ/qAwpWQ0PGsY.jpg"}"
                 style="max-height: 800px; max-width: 600px">
            <form action="/profile" method="post" enctype="multipart/form-data">
                <input type="hidden" name="name" value="image">
                <input type="file" name="value">
                <input type="submit" value="Обновить своё фото">
            </form>
        </div>
        <div style="float: right">
            <h3>Логин: ${user.login}</h3>
            <h3>Имя: ${user.name}</h3>
            <form action="/profile" method="post">
                <input type="hidden" name="name" value="name">
                <input type="text" name="value">
                <input type="submit" value="Обновить имя">
            </form>
            <h3>Фамилия: ${user.surname}</h3>
            <form action="/profile" method="post">
                <input type="hidden" name="name" value="surname">
                <input type="text" name="value">
                <input type="submit" value="Обновить фамилию">
            </form>
            <h3>Отчество: ${user.middleName}</h3>
            <form action="/profile" method="post">
                <input type="hidden" name="name" value="middleName">
                <input type="text" name="value">
                <input type="submit" value="Обновить отчество">
            </form>
            <h3>Email: ${user.email}</h3>
            <h3>Дата рождения: ${user.birth}</h3>
            <form action="/profile" method="post">
                <input type="hidden" name="name" value="birth">
                <input type="date" name="value">
                <input type="submit" value="Обновить дату рождения">
            </form>
            <h3>Номер карты: ${user.card.id}</h3>
            <h3>Баланс карты: ${user.card.balance}</h3>
        </div>
    </div>
</@>