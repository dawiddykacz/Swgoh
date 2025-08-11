const { sendRequest } = require('./connection'); 
const { getEnpointWithBasicUrl } = require('./UrlRepository'); 

async function addRegisterRequest(discordId, allyCode){
    const url = getEnpointWithBasicUrl("register");
    const data = {
        discordId,
        allyCode
    };

    try{
        const response = sendRequest(url,'POST',data)
        console.log(response)
    }catch(error){
        console.log(error.message)
    }
}

module.exports = { addRegisterRequest };