<html lang="ru">
<head>
<title>Create</title>
<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value
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
</style>
</head>
<body>
<form>
    <label for="code_snippet">Code</label><textarea id="code_snippet"></textarea>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</form>
</body>
</html>