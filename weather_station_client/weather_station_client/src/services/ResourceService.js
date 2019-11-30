import axios from 'axios';

const getResource = (url) => {
    const headers = {
        "Content-Type" : "application/x-www-form-urlencoded"
    };

    console.log(headers);
    const req = axios.get(url,{headers: headers});
    return req;
}

export {getResource};