function toggleUserMenu() {
    const menu = document.getElementById('user-dropdown');
    const arrow = document.getElementById('user-menu-arrow');

    // Alternar visibilidad
    menu.classList.toggle('hidden');

    // Rotar la flecha si quieres un efecto chulo
    if (menu.classList.contains('hidden')) {
        arrow.style.transform = 'rotate(0deg)';
    } else {
        arrow.style.transform = 'rotate(180deg)'; // Apunta abajo si se abre
    }
}

// Cerrar si haces clic fuera (Mejora de UX importante)
document.addEventListener('click', function(event) {
    const container = document.getElementById('user-menu-container');
    const menu = document.getElementById('user-dropdown');
    const arrow = document.getElementById('user-menu-arrow');

    if (!container.contains(event.target) && !menu.classList.contains('hidden')) {
        menu.classList.add('hidden');
        arrow.style.transform = 'rotate(0deg)';
    }
});