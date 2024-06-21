/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        "primary": "#0078C8",
        "secondary": "#A8DCFF",
        "error": "#C82400",
        "secondary-error": "#FFB5A5",
        "gray": "#323232",
        "content-text": "#050505",
        "background": "#F2FAFF",
      },
    },
  },
  plugins: [],
}

