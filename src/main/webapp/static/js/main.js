import {dataHandler} from "./dataHandler.js";

const addToCartButtons = document.querySelectorAll(".add-to-cart");
const cartInputFields = document.querySelectorAll(".cart-input");

for (const addToCartButton of addToCartButtons) {
    addToCartButton.addEventListener('click', () => {
        console.log(addToCartButton.dataset.id);
        dataHandler._api_get(`/add-cart?productId=${addToCartButton.dataset.id}`)
    });
}

for (const cartInputField of cartInputFields) {
    cartInputField.addEventListener('keydown', e => {
        if (e.key === "Enter") {
            dataHandler._api_get(`/set-cart?productId=${cartInputField.dataset.id}&newAmount=${cartInputField.value}`);
            cartInputField.blur();
        }
    })
}