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
            throw new Error("City not found, Try another city");
        }

        const data = await response.json();

        const resultContainer = document.getElementById("resultContainer");
        resultContainer.style.display = "block"; // Make container visible

        // Update with all data, including temperature, feels like, and wind speed
        document.getElementById("weatherResult").innerHTML = `
            <b>City</b>: <strong>${data.cityName}</strong><br>
            <b>Temperature</b>: <strong>${data.temperature} °C</strong><br>
            <b>Feels Like</b>: <strong>${data.feelsLike} °C</strong><br>
            <b>Humidity</b>: <strong>${data.humidity} %</strong><br>
            <b>Wind Speed</b>: <strong>${data.windSpeed} m/s</strong><br>
            <b>Weather Condition</b>: <strong>${data.description}</strong><br>
        `;
    } catch (error) {
        const resultContainer = document.getElementById("resultContainer");
        resultContainer.style.display = "block"; // Make container visible
        document.getElementById("weatherResult").innerHTML = `
            <p style="color: red; font-weight: 500;">Error: ${error.message}</p>
        `;
    }
}