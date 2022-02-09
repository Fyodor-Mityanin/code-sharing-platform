<html lang="ru">
<head>
<title>Create</title>
<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": parseInt(document.getElementById("time_restriction").value),
            "views": parseInt(document.getElementById("views_restriction").value)
        };
        let json = JSON.stringify(object);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status === 200) {
          alert("Success!");
        }
    }
</script>
<style>
    pre {
        background-color: #d5d2d2;
        border: solid;
    }
    span {
    color: green;
    }
    input, textarea {
        display: block;
        font-size:11px;
        padding:4px 2px;
        border:solid 1px #aacfe4;
        width:70px;
        margin:2px 0 20px 10px;
    }
    label {
        font-size: 16px;
        padding:4px 2px;
        width:70px;
        margin:2px 0 20px 10px;
    }
</style>
</head>
<body>
<form>
    <label for="code_snippet">Code</label><textarea id="code_snippet"></textarea>
    <label for="time_restriction">Time restriction</label><input id="time_restriction" type="text"/>
    <label for="views_restriction">Views restriction</label><input id="views_restriction" type="text"/>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</form>
</body>
</html>