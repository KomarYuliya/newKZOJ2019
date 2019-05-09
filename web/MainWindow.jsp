<%--
  Created by IntelliJ IDEA.
  User: Юля
  Date: 21.03.2019
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Главная страница</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Demo Background */
        body{background-color: #bdff93;)}
        .active{color: #bdff93;)}
        .form-horizontal{
            background: #fff;
            padding-bottom: 40px;
            border-radius: 15px;
            text-align: justify;
        }
        .form-horizontal .heading{
            display: block;
            font-size: 35px;
            font-weight: 700;
            padding: 35px 0;
            border-bottom: 1px solid #f0f0f0;
            margin-bottom: 30px;
        }
        .form-horizontal .form-group{
            padding: 0 40px;
            margin: 0 0 25px 0;
            position: relative;
        }
        .form-horizontal .form-control{
            background: #f0f0f0;
            border: none;
            border-radius: 20px;
            box-shadow: none;
            padding: 0 20px 0 45px;
            height: 40px;
            transition: all 0.3s ease 0s;
        }
        .form-horizontal .form-control:focus{
            background: #e0e0e0;
            box-shadow: none;
            outline: 0 none;
        }
        .form-horizontal .form-group i{
            position: absolute;
            top: 12px;
            left: 60px;
            font-size: 17px;
            color: #c8c8c8;
            transition : all 0.5s ease 0s;
        }
        .form-horizontal .form-control:focus + i{
            color: #00b4ef;
        }
        .form-horizontal .main-checkbox label{
            width: 20px;
            height: 20px;
            position: absolute;
            top: 0;
            left: 0;
            cursor: pointer;
        }
        .form-horizontal .main-checkbox label:after{
            content: "";
            width: 10px;
            height: 5px;
            position: absolute;
            top: 5px;
            left: 4px;
            border: 3px solid #fff;
            border-top: none;
            border-right: none;
            background: transparent;
            opacity: 0;
            -webkit-transform: rotate(-45deg);
            transform: rotate(-45deg);
        }
        .form-horizontal .text{
            float: left;
            margin-left: 7px;
            line-height: 20px;
            padding-top: 5px;
            text-transform: capitalize;
        }
        .form-horizontal .btn{
            float: top;
            font-size: 14px;
            color: #fff;
            background: #00b4ef;
            border-radius: 30px;
            padding: 10px 25px;
            border: none;
            text-transform: capitalize;
            transition: all 0.5s ease 0s;
        }
        @media only screen and (max-width: 479px){
            .form-horizontal .form-group{
                padding: 0 25px;
            }
            .form-horizontal .form-group i{
                left: 45px;
            }
            .form-horizontal .btn{
                padding: 10px 20px;
            }
        }
        .btn{
            float: top;
            font-size: 14px;
            color: #fff;
            background: #00b4ef;
            border-radius: 30px;
            padding: 10px 25px;
            border: none;
            text-transform: capitalize;
            transition: all 0.5s ease 0s;
        }
        .for{
            float: top;
            padding-bottom: 40px;
            border-radius: 15px;
            text-align: center;
        }
        .otstup{
            display: block;
            font-size: 35px;
            font-weight: 700;
            padding: 35px 0;
            margin-bottom: 30px;
        }
        p {
            margin-right: 22px;margin-left: 22px;
        }
        h1{text-align: center}
        h4{text-align: center}
    </style>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/diaryf">Главная</a></li>
            <li><a href="/diary">Дневник</a></li>
            <li><a href="#">Статистика</a></li>
            <li><a href="/change">Личный кабинет</a></li>
            <li><a href="/control">Добавить блюдо</a></li>
            <li><a href="/">Выйти</a></li>
        </ul>
    </nav>
</div>
<h4>
    <strong>
        <mark style="background-color: azure">Добро пожаловать, ${requestScope.userName}!</mark>
    </strong>
</h4>
<p class="help-block">${requestScope.previousOperation}</p>
<div class="container">
    <div class="form-horizontal">
        <h1>Oсновные принципы правильного (здорового) питания:</h1>
        <p>1. Суточный рацион должен содержать достаточное количество белков, жиров, углеводов, витаминов, минеральных веществ.
            Количество белка в рационе должно соответствовать физиологической норме — 1-1,5 г на 1кг веса. Животный белок должен
            составлять не менее 6094 от суточного количества белка (обязательно присутствие в рационе постного мяса, рыбы, яиц,
            молока и кисломолочных продуктов).</p>
        <p>2. Оптимальное количество жиров — 0,8-1,0 г на 1 кг веса в сутки. Жиры дольше задерживаются в желудке,
            уменьшают возбудимость головного мозга, устраняя чувство голода. Растительные жиры повышают активность
            ферментов, стимулирующих процесс распада жира в организме. В рационе от общего количества жиров должно
            быть 30-3594 растительных масел для приготовления пищи и добавления в салаты.</p>
        <p>З. Желательно ограничить количество углеводов до 3-3,5 г на 1 кг веса в сутки, прежде всего за счет простых: сахар, сладости.</p>
        <p>4. Количество приемов пищи в течение дня не менее 4-5 раз (З основных приема пищи и 2 дополнительных, представленных
            свежими фруктами и овощами, лучше в сыром виде). Основная калорийность рациона должна приходиться на первую половину дня.</p>
        <p>5. Интервалы между приемами пищи не должны превышать 3,5-4 часа.</p>
        <p>6. Количество свободной жидкости не менее l,5J1 в сутки (при отсутствии противопоказаний). Пить и использовать
            для приготовления пищи лучше всего бутилированную или фильтрованную дома воду. Для питья можно использовать
            минеральную негазированную воду с наименьшей степенью минерализации, свежеотжатые соки, чай, кофе. Лучше не
            употреблять кофе натощак, после 18 часов желательно не употреблять зеленый чай, чай каркадэ, кофе, соки из кислых фруктов.</p>
        <p>7. Утром натощак желательно выпить стакан жидкости комнатной температуры. Интервал между последним приемом жидкости и
            едой должен быть 20-30 минут, между едой и последующим приемом жидкости не менее 30 минут. Оптимальное соотношение между
            твердой и жидкой частями пищи во время одного приема должно быть не менее 2:1.</p>
        <p>8. Последний прием пищи - за 2,5-3 часа до сна.</p>
        <p>9. Есть не спеша, тщательно пережевывая пищу, вставать из-за стола, как только почувствуете чувство насыщения, а не тогда,
            когда готовы лопнуть.</p>
        <p>10. Старайтесь избегать в одном блюде сочетаний: белковые продукты (мясо, птица, рыба, морепродукты) и продукты, богатые
            углеводами (крупы, хлеб, отруби, картофель, сладкие фрукты и ягоды).</p>
        <p>11. Ограничить потребление соли до 5г. в сутки.</p>
        <p>12. Питание должно быть максимально разнообразным. В желудочнокишечном тракте человека присутствует достаточное
            количество ферментов, способных расщепить различные продукты.</p>
        <p>13. Избегайте многокомпонентных блюд. За один прием не смешивайте более 3-4 видов продуктов (не считая специи и растительное масло).</p>
        <p>14. Особенно полезны овощи, содержащие пектин и клетчатку. Они создают чувство насыщения, регулируют функцию кишечника, выводят шлаки.
            К ним относятся капуста, кабачок, редис, томаты, огурцы, тыква, листовая зелень.</p>
        <p>15. Овощи можно употреблять сырыми, тушеными, вареными, приготовленными на пару и на гриле. Свежие овощи предпочтительнее
            употреблять с растительным маслом (подсолнечное, оливковое, льняное). При варке овощей вода не должна полностью закрывать
            их поверхность. Соль, растительное масло и натуральные специи лучше добавлять на заключительном этапе приготовления пищи.</p>
        <p>16. Мясо, птицу, рыбу можно запекать, готовить на пару, гриле. Употреблять данные продукты лучше с растительным гарниром.
            Кожицу птицы и рыбы желательно в пищу не употреблять.</p>
        <p>17. Способы приготовления продуктов (по убыванию качества): гриль (аэрогриль, гриль в печи СВЧ, барбекю, мангал);
            приготовление пищи на пару; запеченное в духовке; соленое; вяленое; отварное; тушеное.</p>
        <p>18. Хлеб обязательно должен присутствовать в рационе, лучше всего зерновой, с отрубями, белковый.</p>
        <p>19. Соусы лучше домашнего приготовления с овощами, пряности натуральные: лавровый лист, перец горошком, петрушка,
            кинза, сельдерей, гвоздика, тмин, чеснок, укроп, гвоздика, имбирь.</p>
    </div>
</div>
</body>
</html>
