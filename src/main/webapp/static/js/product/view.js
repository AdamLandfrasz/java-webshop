export function ProductView(routingManager) {
    this.routingManager = routingManager;
    routingManager.install('/products/all', this.renderAll.bind(this));
}

ProductView.prototype.renderAll = function() {
    this.routingManager.setRoute('/products/all');
    const appRoot = document.getElementById('app');
    const products = [{
        name: 'Phone',
        description: `A simple phone.

It is very cool`,
        price: 100,
        picUrl: '/static/img/product_1.jpg'
    }, {
        name: 'Tablet',
        description: `A very cool tablet.

It can do everything.`,
        price: 150,
        picUrl: '/static/img/product_2.jpg'
    }];
    const productElements = products.map(productToElement);

    const productContainer = document
          .getElementById('product-container-template')
          .content
          .cloneNode(true);

    const productGrid = productContainer.querySelector('.product-grid');

    productElements.forEach(p => productGrid.appendChild(p));
    productContainer.querySelector('.filter-criteria').innerText = 'All products';

    appRoot.appendChild(productContainer);
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
