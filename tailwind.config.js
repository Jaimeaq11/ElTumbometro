/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./src/main/resources/templates/**/*.html",
        "./src/main/resources/static/**/*.js"
    ],
    theme: {
        extend: {
            colors: {
                'fondo': '#121212',
                'primario': '#0d51f4',   // Un azul racing agresivo
                'primario_hover': '#0a41c5'
            },
            fontFamily: {
                'Poppins': ['Poppins', 'sans-serif'],
                'Google_sans': ['"Google Sans"', 'sans-serif']
            }
        },
    },
    plugins: [],
}