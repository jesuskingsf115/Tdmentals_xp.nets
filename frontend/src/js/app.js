const statusElement = document.querySelector("#api-status");
const button = document.querySelector("#check-api");

async function checkApi() {
  statusElement.textContent = "Verificando conexão...";
  try {
    const response = await fetch("/api/health");
    if (!response.ok) throw new Error(`HTTP ${response.status}`);
    const data = await response.json();
    statusElement.textContent = `API ${data.status} — ${data.application}`;
  } catch (error) {
    statusElement.textContent = "Não foi possível conectar à API. Verifique os containers.";
    console.error(error);
  }
}

button.addEventListener("click", checkApi);
checkApi();
