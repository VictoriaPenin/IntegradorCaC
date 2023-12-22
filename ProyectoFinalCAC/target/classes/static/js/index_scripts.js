document.getElementById('filtro').addEventListener('input', function () {
    const filtro = this.value.toLowerCase();
    const filas = document.querySelectorAll('tbody tr');

    filas.forEach(function (fila) {
        const contenido = fila.textContent.toLowerCase();
        if (contenido.includes(filtro)) {
            fila.style.display = 'table-row';
        } else {
            fila.style.display = 'none';
        }
    });
});