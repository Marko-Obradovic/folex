<script lang="ts">
  import watch from '$lib/assets/watch.jpg';
  import watch1 from '$lib/assets/watch1.jpg';
  import watch2 from '$lib/assets/watch2.jpg';
  import watch3 from '$lib/assets/watch3.jpg';
  import watch4 from '$lib/assets/watch4.jpg';
  import watch5 from '$lib/assets/watch5.jpg';
  import watch6 from '$lib/assets/watch6.jpg';
  import type { PageProps } from './$types';

  let { form }: PageProps = $props();

  const watches = [
    {
      name: 'Pocket Watch',
      price: '£299',
      image: watch1
    },
    {
      name: 'Wrist Watch',
      price: '£189',
      image: watch2
    },
    {
      name: 'Leather Watch',
      price: '£89',
      image: watch3
    },
    {
      name: 'Neo Watch',
      price: '£139',
      image: watch4
    },
    {
      name: 'Christmas Watch',
      price: '£239',
      image: watch5
    },
    {
      name: 'Classic Leather Watch',
      price: '£439',
      image: watch6
    }
  ]
</script>

{#if form?.success}
<div id="sticky-banner" tabindex="-1" class="fixed top-0 start-0 z-50 flex justify-between w-full p-4 border-b border-gray-200 bg-gray-50 dark:bg-gray-700 dark:border-gray-600">
  <div class="flex items-center mx-auto">
      <p class="flex items-center text-sm font-normal text-gray-500 dark:text-gray-400">
        <span>Order Placed!</span>
      </p>
  </div>
</div>
{/if}

<div class="section">
  <h1 class="text-9xl text-center italic font-light">Wedding Watches</h1>
  <img alt="Wedding Watches" src={watch} />
</div>

<div class="container m-auto">

<div class="grid grid-cols-2 gap-8" >
  {#each watches as watch}
    <div class="mt-20">
      <h2 class="text-2xl text-center">{watch.name}</h2>
      <h3 class="text=xl text-center italic">{watch.price}</h3>
      <div>
        <img alt="watch" src={watch.image} class="h-96 w-full"/>
      </div>
      <div class="m-auto">
        <form method="POST">
            <input type="hidden" value={watch.name} name="description" />
            <input type="submit" class="text-white bg-gradient-to-r w-full from-green-400 via-green-500 to-green-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 shadow-lg shadow-green-500/50 dark:shadow-lg dark:shadow-green-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2" value="Place Order" />
        </form>
      </div>
    </div>
  {/each}
</div>

</div>