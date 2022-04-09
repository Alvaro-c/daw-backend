
Date.prototype.toDateInputValue = (function () {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0, 10);
});

function validateForm(){

    let adults = parseInt(document.getElementById('adults-header15-2m').value);
    let children = parseInt(document.getElementById('children-header15-2m').value);
    let avail = parseInt(document.getElementById('avail').innerHTML);
    let date = document.getElementById('visitdate-header15-2m').value;
    let message = document.getElementById('message-header15-2m').value;
    let phone = document.getElementById('phone-header15-2m').value;
    let today = new Date().toDateInputValue();
    let dateD = new Date(date);
    let todayD = new Date(today);


    if (adults + children <=0) {

        console.log('Reserva para 0 personas')
        return false;
    }

    if (adults < 0 || children < 0) {

        console.log('Reserva con valores negativos')
        return false;
    }

    if ((adults + children) > avail) {

        console.log('Reserva para más plazas de las permitidas')
        return false;
    }


    if(dateD < todayD) {

        console.log('Reserva en una fecha pasada');
        return false;
    }

    

    /* Control de campos vacios para adult y children */
    if(Number.isNaN(adults)) {

        console.log('FLAG adults' + adults);
        document.getElementById('adults-header15-2m').value = 0;
    }

    if(Number.isNaN(children)) {

        console.log('FLAG children' + children);
        document.getElementById('children-header15-2m').value = 0;
    }


}




function validateCheckDate() {

    let date = document.getElementById('datePicker').value
    let today = new Date().toDateInputValue();
    let dateD = new Date(date);
    let todayD = new Date(today);


    if(dateD < todayD) {

        console.log('Reserva en una fecha pasada');

        return false;
    }
}

