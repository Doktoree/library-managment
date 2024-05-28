import { func } from "prop-types";

const apiUrl = "http://localhost:8000/api/member";

export async function createMember(member) {
  const response = await fetch(apiUrl, {
    method: "POST",
    body: JSON.stringify(member),
  });

  const data = await response.json();

  return data;
}

export async function saveMember(member, id) {
  const response = await fetch(apiUrl + id, {
    method: "PATCH",
    body: JSON.stringify(member),
  });

  const data = await response.json();

  return data;
}

export async function searchMember(member) {
  const response = await fetch(apiUrl + "/search", {
    method: "GET",
    body: JSON.stringify(member),
  });
  const data = await response.json();

  return data;
}

export async function getMember(id) {
  const response = await fetch(apiUrl + id);
  const data = await response.json();

  return data;
}

export async function getAllMembers() {
  const response = await fetch(apiUrl);
  const data = await response.json();

  return data;
}

export async function deleteMember(id) {
  const response = await fetch(apiUrl + id, {
    method: "DELETE",
  });
  const data = await response.json();

  return data;
}
