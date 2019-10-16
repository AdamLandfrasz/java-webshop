export function init() {
    window.addEventListener('DOMContentLoaded', (event) => {
        const appRoot = document.getElementById('app');
        const product = {
            name: 'Phone',
            description: `A simple phone.

It is very cool`,
            price: 100,
            picUrl: '/static/img/product_1.jpg'
        };
        const productElement = productToElement(product);
        appRoot.appendChild(productElement);
    });
}

function productToElement(product) {
    const fragment = document
          .getElementById('product-template')
          .content
          .cloneNode(true);

    fragment.querySelector('.product-name').innerText = product.name;
    fragment.querySelector('.product-description').innerText = product.description;
    fragment.querySelector('.product-picture').src = product.picUrl;
    fragment.querySelector('.product-price').innerText = product.price;

    return fragment;
}

init();
