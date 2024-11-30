document.getElementById("getWeatherBtn").addEventListener("click", getWeather);
document.getElementById("cityInput").addEventListener("keypress", (e) => {
    if (e.key === "Enter") getWeather(); // Handle 'Enter' key
});

async function getWeather() {
    const city = document.getElementById("cityInput").value;
    if (!city) {
        alert("Please enter a city name!");
        return;
    }

    try {
        const response = await fetch(`/api/weather/${city}`);
        if (!response.ok) {
            throw new Error("City not found");
        }

        const data = await response.json();

        const resultContainer = document.getElementById("resultContainer");
        resultContainer.style.display = "block"; // Make container visible

        // Update with all data, including temperature
        document.getElementById("weatherResult").innerHTML = `
            City: <strong>${data.cityName}</strong><br>
            Weather Condition: <strong>${data.description}</strong><br>
            Temperature: <strong>${data.temperature} °C</strong>
        `;
    } catch (error) {
        const resultContainer = document.getElementById("resultContainer");
        resultContainer.style.display = "block"; // Make container visible
        document.getElementById("weatherResult").innerHTML = `
            <p style="color: red;">Error: ${error.message}</p>
        `;
    }
}
