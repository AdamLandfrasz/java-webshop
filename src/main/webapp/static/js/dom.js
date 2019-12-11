import {dataHandler} from "./dataHandler.js";

export let dom = {
    getCart: function (response) {
        let cart = response;
        let tableContent = '';
        const sumPrice = {sum: 0.00};

        for (let product of cart) {
            sumPrice.sum += product.price * product.amount;
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
                    <td class="align-middle">${(product.price * product.amount).toFixed(2)} EUR</td>
                </tr>`
        }
        tableContent +=
            `<tr>
                <td colspan="5"></td>
            </tr>
            <tr>
                <td colspan="3"></td>
                <td class="align-middle">Summed Price:</td>
                <td class="align-middle">${sumPrice.sum.toFixed(2)} EUR</td>
            </tr>`;
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
                } else {
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
            let cart = $('#cart');
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
    },

    handleNav: function () {
        let nav = document.querySelector('.sidebar');
        let container = document.querySelector('.container-fluid');
        let topRow = container.querySelector('#top-row');
        console.log(nav.style.width);
        if (nav.style.width === '0px' || nav.style.width === "") {
            nav.style.width = '250px';
            container.style.marginLeft = "250px";
            topRow.style.left = "250px";
        } else {
            nav.style.width = '0px'
            container.style.marginLeft = "0px";
            topRow.style.left = "0";
        }
    },

    initNav: function () {
        document.querySelector('#nav-toggle').addEventListener('click', () => {
            this.handleNav();
        });
    },

    initCheckout: function () {
        let addressOpenBtn = document.querySelector('#address-open');
        let orderContainer = document.querySelector('.order-container');
        let addressContainer = document.querySelector('.address-container');
        addressOpenBtn.addEventListener('click', function () {
            if (addressOpenBtn.dataset.open === '0') {
                addressOpenBtn.dataset.open = '1';
                addressOpenBtn.innerText = 'Back to your order';

                orderContainer.style.maxHeight = '0';
                addressContainer.style.maxHeight = '1000px';
            } else {
                addressOpenBtn.dataset.open = '0';
                addressOpenBtn.innerText = 'Continue to billing details';

                orderContainer.style.maxHeight = '1000px';
                addressContainer.style.maxHeight = '0';
            }
        });
    }
};