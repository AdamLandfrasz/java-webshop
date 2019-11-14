import {dataHandler} from "./dataHandler.js";

export let dom = {
    getCart: function (response) {
        let cart = response
        let tableContent = '';

        for (let product of cart) {
            tableContent +=
                `<tr>
                <td>
                    <img class="img-fluid" src="/static/img/product_${product.id}.jpg" alt="">
                </td>
                <td>${product.name}</td>
                <td>
                    <input class="cart-input" data-id="${product.id}" value="${product.amount}"
                           type="number" required min="0" value="1">
                </td>
                </tr>`
        }
        document.getElementById("table-body-content").innerHTML = tableContent;
        dom.initCartInput();
    },

    initCartInput: function () {
        const cartInputFields = document.querySelectorAll(".cart-input");

        for (const cartInputField of cartInputFields) {

            cartInputField.addEventListener('change', () => {
                dataHandler._api_get(
                    `/set-cart?productId=${cartInputField.dataset.id}&newAmount=${cartInputField.value}`,
                    (response) => {
                        dom.getCart(response);
                    });
            });
        }
    }
};