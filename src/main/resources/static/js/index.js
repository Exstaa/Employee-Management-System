async function login(e){
    e.preventDefault();
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;


    let resultUser = await fetch('http://localhost:8080/api/accounts/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: username
    }).then(response=> {
        return response.json()
    })
    if(resultUser.password == password){
        document.getElementById("login-container").style.display = 'none';
        document.getElementById("employees-container").style.display = 'block';

    }

}

function getAllEmployees() {
    fetch('http://localhost:8080/api/employees/')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('employeeTableBody');
            tableBody.innerHTML = ''; 
            data.forEach(employee => {
                const row = `
                    <tr>
                        <td>${employee.id}</td>
                        <td><input type="text" value="${employee.name}" class="form-control" id="name-${employee.id}"></td>
                        <td><input type="text" value="${employee.surname}" class="form-control" id="surname-${employee.id}"></td>
                        <td><input type="email" value="${employee.email}" class="form-control" id="email-${employee.id}"></td>
                        <td><input type="text" value="${employee.phoneNumber}" class="form-control" id="phoneNumber-${employee.id}"></td>
                        <td><input type="number" value="${employee.salary}" class="form-control" id="salary-${employee.id}"></td>
                        <td><input type="text" value="${employee.status}" class="form-control" id="status-${employee.id}"></td>
                        <td><input type="text" value="${employee.hireDate}" class="form-control" id="hireDate-${employee.id}"></td>
                        <td>
                            <button class="btn btn-primary" onclick="saveEmployee(${employee.id})">Save</button>
                            <button class="btn btn-danger" onclick="deleteEmployee(${employee.id})">Delete</button>
                        </td>
                    </tr>
                `;
                tableBody.insertAdjacentHTML('beforeend', row);
            });
        })
        .catch(error => console.error('Error fetching employees:', error));
}

function saveEmployee(id) {
    const updatedEmployee = {
        id: id,
        name: document.getElementById(`name-${id}`).value,
        surname: document.getElementById(`surname-${id}`).value,
        email: document.getElementById(`email-${id}`).value,
        phoneNumber: document.getElementById(`phoneNumber-${id}`).value,
        salary: document.getElementById(`salary-${id}`).value,
        status: document.getElementById(`status-${id}`).value,
        hireDate: document.getElementById(`hireDate-${id}`).value
    };

    fetch(`http://localhost:8080/api/employees/edit`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedEmployee)
    })
    .then(response => {
        if (response.ok) {
            alert('Employee updated successfully!');
            getAllEmployees();
        } else {
            alert('Error updating employee');
        }
    })
    .catch(error => console.error('Error updating employee:', error));
}

function deleteEmployee(id) {
    if (confirm('Are you sure you want to delete this employee?')) {
        fetch(`http://localhost:8080/api/employees/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Employee deleted successfully!');
                getAllEmployees(); 
            } else {
                alert('Error deleting employee');
            }
        })
        .catch(error => console.error('Error deleting employee:', error));
    }
}

document.addEventListener('DOMContentLoaded', getAllEmployees);