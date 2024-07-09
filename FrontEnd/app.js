 98r form = document.getElementById("myForm"),
    imgInput = document.querySelector(".img"),
    file = document.getElementById("imgInput"),
    userName = document.getElementById("name"),
    email = document.getElementById("email"),
    phone = document.getElementById("phone"),
    city = document.getElementById("city"),
    size = document.getElementById("size"),
    quantity = document.getElementById("quantity"),
    outfit = document.getElementById("outfit"),
    sDate = document.getElementById("sDate"),
    submitBtn = document.querySelector(".submit"),
    userInfo = document.getElementById("data"),
    modal = document.getElementById("userForm"),
    modalTitle = document.querySelector("#userForm .modal-title"),
    newUserBtn = document.querySelector(".newUser")


let getData = localStorage.getItem('userProfile') ? JSON.parse(localStorage.getItem('userProfile')) : []

let isEdit = false, editId
showInfo()

newUserBtn.addEventListener('click', ()=> {
    submitBtn.innerText = 'Submit',
    modalTitle.innerText = "Fill the Form"
    isEdit = false
    imgInput.src = "./images/ProfileIcon.webp"
    form.reset()
})


file.onchange = function(){
    if(file.files[0].size < 1000000){  // 1MB = 1000000
        var fileReader = new FileReader();

        fileReader.onload = function(e){
            imgUrl = e.target.result
            imgInput.src = imgUrl
        }

        fileReader.readAsDataURL(file.files[0])
    }
    else{
        alert("This file is too large!")
    }
}


function showInfo(){
    document.querySelectorAll('.employeeDetails').forEach(info => info.remove())
    getData.forEach((element, index) => {
        let createElement = `<tr class="employeeDetails">
            <td>${index+1}</td>
            <td><img src="${element.picture}" alt="" width="50" height="50"></td>
            <td>${element.employeeName}</td>

            <td>${element.employeeEmail}</td>
            <td>${element.employeePhone}</td>
            <td>${element.employeeCity}</td>
            <td>${element.employeesize}</td>
            <td>${element.employeequantity}</td>
            <td>${element.employeeoutfit}</td>
            <td>${element.startDate}</td>


            <td>
                <button class="btn btn-success" onclick="readInfo('${element.picture}', '${element.employeeName}', '${element.employeeEmail}', '${element.employeePhone}', '${element.employeeCity}', '${element.employeequantity}','${element.employeeoutfit}', '${element.startDate}')" data-bs-toggle="modal" data-bs-target="#readData"><i class="bi bi-eye"></i></button>

                <button class="btn btn-primary" onclick="editInfo(${index}, '${element.picture}', '${element.employeeName}', '${element.employeeEmail}', '${element.employeePhone}', '${element.employeeCity}', '${element.employeequantity}', '${element.employeeoutfit}', '${element.startDate}')" data-bs-toggle="modal" data-bs-target="#userForm"><i class="bi bi-pencil-square"></i></button>

                <button class="btn btn-danger" onclick="deleteInfo(${index})"><i class="bi bi-trash"></i></button>
                            
            </td>
        </tr>`

        userInfo.innerHTML += createElement
    })
}
showInfo()


function readInfo(pic, name, email, phone, city, size, quantity, outfit, sDate){
    document.querySelector('.showImg').src = pic,
    document.querySelector('#showName').value = name,
    document.querySelector("#showEmail").value = email,
    document.querySelector("#showPhone").value = phone,
    document.querySelector("#showcity").value = city,

    document.querySelector("#showsize").value = size,
    document.querySelector("#showquantity").value = quantity,
    document.querySelector("#showoutfit").value = outfit,

    document.querySelector("#showsDate").value = sDate
}


function editInfo(index, pic, name, Email, Phone, city, size, quantity, outfit, Sdate){
    isEdit = true
    editId = index
    imgInput.src = pic
    userName.value = name
    Email.value = Email
    Phone.value =Phone
    city.value = city,
    size.value = size,
    quantity.value = quantity,
    outfit.value = outfit,
    sDate.value = Sdate

    submitBtn.innerText = "Update"
    modalTitle.innerText = "Update The Form"
}


function deleteInfo(index){
    if(confirm("Are you sure want to delete?")){
        getData.splice(index, 1)
        localStorage.setItem("userProfile", JSON.stringify(getData))
        showInfo()
    }
}

function success(){
    alert("Transaction Successfully !");
}

form.addEventListener('submit', (e)=> {
    e.preventDefault()

    const information = {
        picture: imgInput.src == undefined ? "./image/ProfileIcon.webp" : imgInput.src,
        employeeName: userName.value,
        employeeEmail: email.value,
        employeePhone: phone.value,
        employeeCity: city.value,
        employeesize: size.value,

        employeequantity: quantity.value,


        employeeoutfit: outfit.value,
        startDate: sDate.value
    }

    if(!isEdit){
        getData.push(information)
    }
    else{
        isEdit = false
        getData[editId] = information
    }

    localStorage.setItem('userProfile', JSON.stringify(getData))

    submitBtn.innerText = "Submit"
    modalTitle.innerHTML = "Fill The Form"

    showInfo()

    form.reset()

    imgInput.src = "images/ProfileIcon.webp"  

    // modal.style.display = "none"
    // document.querySelector(".modal-backdrop").remove()

})