function authFetch(url, options = {}) {
    const token = localStorage.getItem("token");

    if (!token) {
        window.location.href = "/html/login.html";
        return;
    }

    options.headers = {
        ...(options.headers || {}),
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
    };

    return fetch(url, options);
}
