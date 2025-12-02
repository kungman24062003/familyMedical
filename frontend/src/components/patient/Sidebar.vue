<template>
  <div class="w-64 bg-white border-r flex flex-col justify-between">
    <div>
      <div class="p-6 border-b-1 border-b-neutral-500 "><h1 class="justify-between items-center align-middle text-center text-xl font-bold ">MedFamily</h1></div>
      <ul class="space-y-3">
        <ul class="space-y-3">
  <router-link
    to="/"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    🏠 Dashboard
  </router-link>

  <router-link
    to="/member"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    👨‍👩‍👧 Family Members
  </router-link>

  <router-link
    to="/doctor"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    🔍 Find Doctor
  </router-link>

  <router-link
    to="/feedback"
    class="flex items-center gap-2 p-2 hover:bg-gray-100 rounded cursor-pointer block"
  >
    💬 Feedback
  </router-link>
</ul>

      </ul>
    </div>
    <div class="flex items-center gap-2 mt-4 p-2 border-t pt-4">
      <div class="w-10 h-10 rounded-full bg-gray-200"></div>
      <div>
        <p class="text-sm font-medium">Nguyen Van A</p>
        <p class="text-xs text-gray-500">nguyenvana@example.com</p>
        <button class="text-xs text-red-500 mt-1">Sign Out</button>
      </div>
    </div>
  </div>
</template>
