/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//phone and nic validation

function validatePhone (phone,evt){ //for onkeypress
    if (evt.keyCode >= 48 && evt.keyCode <= 57){
        return true;
    }else{
        return false;
    }
}

function validateNic (nic){ //for onblur
    //regex exp(nic) = str.match('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]V');
    res = nic.value.match('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]V');
 if (res === null){
    alert("NIC is invalid!!");
 }
}