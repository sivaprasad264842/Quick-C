// Smooth scroll for navigation links
document.querySelectorAll(".nav-menu a").forEach((link) => {
    link.addEventListener("click", function (e) {
        e.preventDefault();
        const targetId = this.getAttribute("href");
        if (targetId.startsWith("#")) {
            const targetElement = document.querySelector(targetId);
            if (targetElement) {
                targetElement.scrollIntoView({ behavior: "smooth" });
            }
        }
    });
});

// Search functionality
const searchInput = document.getElementById("searchInput");
const searchIcon = document.querySelector(".search-icon");

searchIcon.addEventListener("click", function () {
    performSearch();
});

searchInput.addEventListener("keypress", function (e) {
    if (e.key === "Enter") {
        performSearch();
    }
});

function performSearch() {
    const searchTerm = searchInput.value.trim().toLowerCase();
    if (searchTerm) {
        console.log("Searching for:", searchTerm);
        // Add your search logic here
        alert(`Searching for: ${searchTerm}`);
    }
}

// Banner button click
const bannerBtn = document.querySelector(".banner-btn");
bannerBtn.addEventListener("click", function () {
    // Scroll to deals section
    const dealsSection = document.querySelector(".deals-section");
    dealsSection.scrollIntoView({ behavior: "smooth" });
});

// Category card click events
const categoryCards = document.querySelectorAll(".category-card");
categoryCards.forEach((card) => {
    card.addEventListener("click", function () {
        const categoryName = this.querySelector(".category-name").textContent;
        console.log("Category clicked:", categoryName);
        // Add navigation logic here
        alert(`Navigating to ${categoryName}`);
    });
});

// Product card click events
const productCards = document.querySelectorAll(".product-card");
productCards.forEach((card) => {
    card.addEventListener("click", function () {
        const productBrand = this.querySelector(".product-brand").textContent;
        const productName = this.querySelector(".product-name").textContent;
        console.log("Product clicked:", productBrand, "-", productName);
        // Add product detail page navigation logic here
        alert(`Opening: ${productBrand} - ${productName}`);
    });
});

// Add to wishlist functionality (icon links)
const wishlistLink = document.querySelectorAll(".icon-link")[1];
wishlistLink.addEventListener("click", function (e) {
    e.preventDefault();
    console.log("Wishlist clicked");
    alert("Wishlist feature - Coming soon!");
});

// Add to bag functionality (icon links)
const bagLink = document.querySelectorAll(".icon-link")[2];
bagLink.addEventListener("click", function (e) {
    e.preventDefault();
    console.log("Bag clicked");
    alert("Shopping bag feature - Coming soon!");
});

// Profile functionality (icon links)
const profileLink = document.querySelectorAll(".icon-link")[0];
profileLink.addEventListener("click", function (e) {
    e.preventDefault();
    console.log("Profile clicked");
    alert("Profile feature - Coming soon!");
});

// Sticky header scroll effect
let lastScroll = 0;
const header = document.querySelector("header");

window.addEventListener("scroll", function () {
    const currentScroll = window.pageYOffset;

    if (currentScroll > 100) {
        header.style.boxShadow = "0 4px 6px rgba(0,0,0,0.15)";
    } else {
        header.style.boxShadow = "0 2px 4px rgba(0,0,0,0.1)";
    }

    lastScroll = currentScroll;
});

// Animation on scroll for product cards
const observerOptions = {
    threshold: 0.1,
    rootMargin: "0px 0px -50px 0px",
};

const observer = new IntersectionObserver(function (entries) {
    entries.forEach((entry) => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = "0";
            entry.target.style.transform = "translateY(20px)";

            setTimeout(() => {
                entry.target.style.transition =
                    "opacity 0.5s ease, transform 0.5s ease";
                entry.target.style.opacity = "1";
                entry.target.style.transform = "translateY(0)";
            }, 100);

            observer.unobserve(entry.target);
        }
    });
}, observerOptions);

// Observe all product cards for animation
document.querySelectorAll(".product-card").forEach((card) => {
    observer.observe(card);
});

// Footer link functionality
const footerLinks = document.querySelectorAll(".footer-section a");
footerLinks.forEach((link) => {
    link.addEventListener("click", function (e) {
        e.preventDefault();
        const linkText = this.textContent;
        console.log("Footer link clicked:", linkText);
        alert(`Navigating to: ${linkText}`);
    });
});

// Logo click - scroll to top
document.querySelector(".logo").addEventListener("click", function () {
    window.scrollTo({ top: 0, behavior: "smooth" });
});

console.log("Quick-C E-commerce site loaded successfully!");
