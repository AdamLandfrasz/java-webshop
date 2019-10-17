import {showProductPage} from './product/view.js';

export function init() {
    window.addEventListener('DOMContentLoaded', (event) => {
        showProductPage();
    });
}

init();
