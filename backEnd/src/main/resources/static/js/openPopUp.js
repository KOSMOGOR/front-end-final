document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll('.openPopupBtn');
    var popup = document.getElementById('popup');
    var overlay = document.getElementById('overlay');

    buttons.forEach(function(button) {
        button.addEventListener('click', function() {
            popup.style.display = 'flex';
            overlay.style.display = 'flex';
        });
    });
});

function cls () {
    var popup = document.getElementById('popup');
    var overlay = document.getElementById('overlay');

    popup.style.display = 'none';
    overlay.style.display = 'none';
};
