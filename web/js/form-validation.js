function validarCampoTextoObrigatorio(id) {
  var el = document.getElementById(id);
  if (el == null) {
    alert("O elemento não existe");
    return false;
  }
  if (el.value == null || el.value.length == 0 || (/^\s+$/.test(el.value))) {
    alert("Informe algum texto válido");
    return false;
  }
  return true;
}

function validarCampoCheckbox(id) {
  var el = document.getElementById(id);
  if (el == null) {
    alert("O elemento não existe");
    return false;
  }
  if (el.checked) {
    alert("Elemento selecionado");
    return true;
  } else {
    alert("Elemento não selecionado");
    return false;
  }
}