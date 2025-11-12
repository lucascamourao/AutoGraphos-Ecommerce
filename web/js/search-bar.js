// Lógica da Barra de Busca

const body = document.querySelector("body");
// vai usar?

const searchInput = document.querySelector("#search-input");
const movieLinks = document.querySelectorAll(".product-details-card");

searchInput.addEventListener("input", () => {
  const searchTerm = searchInput.value.toLowerCase();

  movieLinks.forEach((link) => {
    const productName = link.querySelector("h3").textContent.toLowerCase();

    if (productName.includes(searchTerm)) {
      link.style.display = "block"; // Mostra o card
    } else {
      link.style.display = "none"; // Esconde o card
    }
  });
});