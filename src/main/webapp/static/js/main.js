import {dataHandler} from "./dataHandler.js";
import {dom} from "./dom.js";

const addToCartButtons = document.querySelectorAll(".add-to-cart");

for (const addToCartButton of addToCartButtons) {
    addToCartButton.addEventListener('click', () => {
        dataHandler._api_get(`/add-cart?productId=${addToCartButton.dataset.id}`)
    });
}

dom.initCartInput();

$('.add-to-cart').on('click', function () {
    let cart = $('#cart-icon');
    let imgToDrag = $(this).parents('.card').find('img').eq(0);
    console.log(imgToDrag);
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
                'z-index': '100'
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


