import {dataHandler} from "./dataHandler.js";

export let dom = {
    getCart: function (response) {
        let cart = response;
        let tableContent = '';

        for (let product of cart) {
            tableContent +=
                `<tr>
                <td class="text-center">
                    <img class="img" src="/static/img/product_${product.id}.jpg" alt="">
                </td>
                <td class="align-middle">${product.name}</td>
                <td class="align-middle">${product.price + " EUR"}</td>
                <td class="align-middle text-center">
                    <input class="cart-input" data-id="${product.id}" value="${product.amount}"
                           type="number" required min="0" value="1">
                </td>
                <td class="align-middle">${product.price*product.amount + " EUR"}</td>
                </tr>`
        }
        document.getElementById("table-body-content").innerHTML = tableContent;
        dom.initCartInput();
    },

    initCartInput: function () {
        const cartInputFields = document.querySelectorAll(".cart-input");

        for (const cartInputField of cartInputFields) {

            cartInputField.addEventListener('change', () => {
                if (cartInputField.value !== "") {
                    dataHandler._api_get(
                        `/set-cart?productId=${cartInputField.dataset.id}&newAmount=${cartInputField.value}`,
                        (response) => {
                            dom.getCart(response);
                        });
                } else  {
                    dataHandler._api_get(
                        `/set-cart?productId=${cartInputField.dataset.id}&newAmount=${cartInputField.defaultValue}`,
                        (response) => {
                            dom.getCart(response);
                        });
                }
            });
        }
    },

    initAddToCartButtons: function () {
        const addToCartButtons = document.querySelectorAll(".add-to-cart");
        for (const addToCartButton of addToCartButtons) {
            addToCartButton.addEventListener('click', () => {
                dataHandler._api_get_no_callback(`/add-cart?productId=${addToCartButton.dataset.id}`);
            });
        }
        dom.initAddCartImgHover();
    },

    initAddCartImgHover: function () {
        $('.add-to-cart').on('click', function () {
            let cart = $('#cart-icon');
            let imgToDrag = $(this).parents('.card').find('img').eq(0);
            if (imgToDrag) {
                let imgClone = imgToDrag.clone()
                    .offset({
                        top: imgToDrag.offset().top,
                        left: imgToDrag.offset().left
                    })
                    .css({
                        'opacity': '0.5',
                        'position': 'absolute',
                        'height': '150px',
                        'width': '150px',
                        'z-index': '5000'
                    })
                    .appendTo($('body'))
                    .animate({
                        'top': cart.offset().top + 10,
                        'left': cart.offset().left + 10,
                        'width': 75,
                        'height': 75
                    }, 1000);

                imgClone.animate({
                    'width': 0,
                    'height': 0
                }, function () {
                    $(this).detach()
                });
            }
        });
    }
};