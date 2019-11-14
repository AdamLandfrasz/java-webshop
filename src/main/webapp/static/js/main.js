import {dataHandler} from "./dataHandler.js";
import {dom} from "./dom.js";

const addToCartButtons = document.querySelectorAll(".add-to-cart");

for (const addToCartButton of addToCartButtons) {
    addToCartButton.addEventListener('click', () => {
        console.log(addToCartButton.dataset.id);
        dataHandler._api_get(`/add-cart?productId=${addToCartButton.dataset.id}`)
    });
}

dom.initCartInput();

