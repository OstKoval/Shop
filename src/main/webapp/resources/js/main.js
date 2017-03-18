function addToCart(product_name) {

        $.ajax(
            {
                url: '/addToCart/' + product_name,
                method: 'GET'
            }
        );

    }

function deleteProduct(id){
    $.ajax(
        {url:'/admin/products/'+id+'/delete',
            method: 'GET',
            success:function () {
                location.reload(true)
            }
        }
    );
}

function deleteUser(id){
    $.ajax(
        {
            url: '/admin/users/' + id + '/delete',
            method: 'GET',
            success: function () {
                location.reload(true)
            }
        }

    );

}
function deleteFromCart(id){
    $.ajax({
        url: '/cart/' + encodeURIComponent(id),
        method: 'GET',
        success:function () {
            location.reload();
        }

    });
}

function filterProducts(category,type) {
    $.ajax({
        url:'/products/'+category+'/'+type,
        method:'GET',
        dataType: 'html',
        success:function (data) {
            $('#product-container').html(data);

        }
    });
}
function searchedProducts(value) {
    $.ajax({
        url:'/search/value='+value,
        method:'GET',
        dataType: 'html',
        success:function (data) {
            $('#product-container').html(data);

        }
    });
    location.href ="#product-container";
    return location.href;
}

$('.add-success').on('click', function(e){
    e.stopPropagation();
    e.target.className+=' success-added'
})

