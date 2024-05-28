import { appendToMemberExpression } from "@babel/types";

const apiUrl = 'http://localhost:8000/api/loan';

export async function createLoan(loan){

    const response = await fetch(apiUrl,{
        method: 'POST',
        body: JSON.stringify(loan)
    });

    const data = await response.json();

    return data;
}


export async function getLoanId(id){

    const response = await fetch(apiUrl + id);
    const data = await response.json();

    return data;

}   


export async function getLoanLoan(loan){

    const response = await fetch(apiUrl, {
        method: 'GET',
        body: JSON.stringify(loan)
    });
    const data = await response.json();

    return data;

}

export async function saveLoan(loan, id){

    const response = await fetch(apiUrl + id, {
        method: 'PATCH',
        body: JSON.stringify(loan)
    });

    const data = await response.json();

    return data;

}