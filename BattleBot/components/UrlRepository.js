const basicUrl = 'http://localhost:8080/api/v1/';

function getEnpointWithBasicUrl(endpointName){
    return `${basicUrl}${endpointName}`
}
