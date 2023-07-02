
    $('document').ready(function () {
    $(".editButton").on('click', function (event) {
        event.preventDefault()
        let href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('#editId').val(user.id)
            $('#editUsername').val(user.username)
            $('#editEmail').val(user.email)
            $('#editPassword').val("")
            $('#editRole').val(user.role)
            $("#editForm").attr('action', href)
        })
        $('#editModal').modal()
    })


    $(".deleteButton").on('click', function (event) {
    event.preventDefault()

    let href = $(this).attr('href');

    $.get(href, function (user, status) {
    $('#deleteId').val(user.id)
    $('#deleteUsername').val(user.username)
    $('#deleteEmail').val(user.email)
    $('#deleteRole').val(user.role)
})
    $("#deleteForm").attr('action', href)
    $('#deleteModal').modal()
})
})


    // Пример отправки POST запроса:
    async function postData(url = '', data = {}) {
        // Default options are marked with *
        const response = await fetch(url, {
            method: 'POST', // *GET, POST, PUT, DELETE, etc.
            mode: 'cors', // no-cors, *cors, same-origin
            cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
            credentials: 'same-origin', // include, *same-origin, omit
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: 'follow', // manual, *follow, error
            referrerPolicy: 'no-referrer', // no-referrer, *client
            body: JSON.stringify(data) // body data type must match "Content-Type" header
        });
        return await response.json(); // parses JSON response into native JavaScript objects
    }

    postData('https://example.com/answer', { answer: 42 })
        .then((data) => {
            console.log(data); // JSON data parsed by `response.json()` call
        });