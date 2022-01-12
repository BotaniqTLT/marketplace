function handler( event ) {
  var target = $( event.target );
target.parent().find("ul").toggle();
 event.preventDefault();
 return false;
}
$( ".ul_gel_lak .header" ).click( handler );
$( ".ul_gel_lak ul" ).hide();




//обращаемся к тегу UL. Событие Клик. находим UL. показываем элементы

// корзина товара
 /* $('.full_cart').click(function() {
      window.location.href="<?=urlgen("/cart")?>" ;
  })*/


 function show_cart(cart){
   if(cart.total>0) {
           $('.empty_cart').removeClass('hidden').addClass('hidden');
           $('.full_cart').removeClass('hidden');
           $('.cart_total').html(cart.total);
           $('.cart_summ').html(cart.summ);

         }
   else{
      $('.empty_cart').removeClass('hidden');
      $('.full_cart').removeClass('hidden').addClass('hidden');
   }

 }

 function edit_cart(id, product_id,count){
 $.ajax({
  type:"POST",
  url:"/cart/"+id,
  data:JSON.stringify({
      productId:product_id,
       count:count,
       }),
  dataType:"json",
   contentType: "application/json; charset=utf-8",
   success: function(res){
// show_cart(res);
       console.log(res);
   }

 })

 }
 $(document).ready(function(){
    var container = $(".cart_add");
    container.find(".buy").click(function(){
    var count =  container.find("input").val();
    edit_cart(1,container.data("id"),count);
   })
   $.ajax({
         type:"GET",
         url:"/cart/1",
   //      data:JSON.stringify({
   //         type:"list",    }),
   //      dataType:"json",
         contentType: "application/json; charset=utf-8",
         success: function(res){
            show_cart(res);
           console.log(res);

         }
       })
 })
