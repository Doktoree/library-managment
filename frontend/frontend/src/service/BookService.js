const apiUrl = "http://localhost:8000/api/book";

export async function getAllBooks() {
  const response = await fetch(apiUrl);
  const data = await response.json();

  return data;
}

export async function createBookProfessionalLiterature(professionalLiterature) {
  const response = await fetch(apiUrl + "/pliterature", {
    method: "POST",
    body: JSON.stringify(professionalLiterature),
  });
  const data = await response.json();

  return data;
}

export async function createBookFiction(fiction) {
  const response = await fetch(apiUrl + "/fiction", {
    method: "POST",
    body: JSON.stringify(fiction),
  });

  const data = await response.json();

  return data;
}

export async function saveBookFiction(fiction, id) {
  const response = await fetch(apiUrl + "/fiction/" + id, {
    method: "POST",
    body: JSON.stringify(fiction),
  });

  const data = await response.json();

  return data;
}

export async function saveBookProfessionalLiterature(
  professionalLiterature,
  id
) {
  const response = await fetch(apiUrl + "/pliterature/" + id, {
    method: "POST",
    body: JSON.stringify(professionalLiterature),
  });

  const data = await response.json();

  return data;
}

export async function getBook(id) {
  const response = await fetch(apiUrl + id);
  const data = await response.json();

  return data;
}

export async function deleteBook(id) {
  const response = await fetch(apiUrl + id, {
    method: "DELETE",
  });
  const data = await response.json();

  return data;
}

export async function searchBook(book) {
  const response = await fetch(apiUrl + "/search", {
    method: "GET",
  });

  const data = await response.json();

  return data;
}
