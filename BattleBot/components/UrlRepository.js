const basicUrl = 'http://localhost:8081/api/v1/';

function getEnpointWithBasicUrl(endpointName){
    return `${basicUrl}${endpointName}`
}

module.exports = {getEnpointWithBasicUrl};
