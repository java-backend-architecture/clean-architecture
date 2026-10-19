@param String error
@param dev.dmitriirussu.clean.architecture.presentation.shared.request.CreateUserRequest request

<h2>Create New User</h2>

<form action="/mvc/users" method="post">

    <label for="name">Name:</label><br>
    <input id="name" type="text" name="name" required>
    <br><br>

    <label for="birthDate">Birth Date:</label><br>
    <input id="birthDate"
           type="date"
           name="birthDate"
           value="${request.birthDate().toString()}"
           required>

    <button type="submit">Create</button>
</form>

@if (error != null) <p>${error}</p> @endif

<a href="/mvc/users">Back to Main Page</a>