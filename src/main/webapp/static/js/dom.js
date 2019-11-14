import {dataHandler} from "./dataHandler.js";

export let dom = {
    loadCart: function () {
        dom.getCart();
    },

    clearCart: function () {
        document.getElementById("table-body-content").innerHTML = "";
    },

    getCart: function () {
        dataHandler.getCart((cart) => {
            let tableContent = '';
            console.log(cart);

            for (let product of cart) {
                console.log(product);
                tableContent +=
                    `<tr>
                <td><img class="img-fluid" src="/static/img/product_${product.id}.jpg" alt=""></td>
                <td>${product.name}</td>
                <td><input class="cart-input" data-id="${product.id}" value="${product.amount}"
                           type="number" required min="0" value="1"></td>
                </tr>`
            }
            document.getElementById("table-body-content").innerHTML = tableContent;
        }, () => {
            dom.initCartInput();
        });
    },

    initCartInput: function () {
        console.log("reeee");
        const cartInputFields = document.querySelectorAll(".cart-input");
        for (const cartInputField of cartInputFields) {
            cartInputField.addEventListener('keydown', e => {
                if (e.key === "Enter") {
                    dataHandler._api_get(`/set-cart?productId=${cartInputField.dataset.id}&newAmount=${cartInputField.value}`);
                    dom.loadCart();
                }
            })
        }
    }
};