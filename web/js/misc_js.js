/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function findPreview(username){
   if (username.value === ""){
       document.getElementById('usernameprev').innerHTML = "Username preview : ";
   }else{
        document.getElementById('usernameprev').innerHTML = "Username preview : " + username.value + "@admin";
   }
}