<!-- src/components/layout/DashboardLayout.vue -->
<template>
  <div class="min-h-screen bg-secondary/30 flex">
    <!-- Desktop Sidebar -->
    <aside class="hidden lg:block w-64 bg-card border-r fixed inset-y-0 z-30">
      <NavContent />
    </aside>

    <!-- Mobile Header & Main Content -->
    <div class="flex-1 lg:ml-64 flex flex-col min-h-screen">
      <header
        class="h-16 bg-card/80 backdrop-blur-md border-b sticky top-0 z-20 px-4 lg:px-8 flex items-center justify-between"
      >
        <div class="flex items-center gap-2 lg:hidden">
          <Sheet v-model:open="isMobileMenuOpen" side="left" class="w-72">
            <template #trigger>
              <Button variant="ghost" size="icon" class="-ml-2">
                <Menu class="w-6 h-6" />
              </Button>
            </template>
            <NavContent />
          </Sheet>
          <span class="font-bold text-lg">MedFamily</span>
        </div>

        <div class="flex-1 flex justify-end items-center gap-4">
          <Button variant="ghost" size="icon" class="relative">
            <Bell class="w-5 h-5 text-muted-foreground" />
            <span
              class="absolute top-2 right-2 w-2 h-2 bg-destructive rounded-full animate-pulse"
            />
          </Button>
        </div>
      </header>

      <main class="flex-1 p-4 lg:p-8 overflow-y-auto">
        <div
          class="max-w-6xl mx-auto animate-in fade-in duration-500 slide-in-from-bottom-4"
        >
          <slot />
        </div>
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Menu, Bell } from 'lucide-vue-next'
import Button from '../ui/button.vue'
import Sheet from '../ui/sheet.vue'
import NavContent from './NavContent.vue'

const isMobileMenuOpen = ref(false)
</script>
