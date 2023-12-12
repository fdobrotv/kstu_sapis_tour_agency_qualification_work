import Image from 'next/image'
import ExampleWithProviders from './Table/TS'
import NavBar from './Components/navBar'
import ToursWithProviders from './Tours/page'

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <NavBar></NavBar>
      <div>
        <ToursWithProviders>
        
        </ToursWithProviders>
      </div>
    </main>
  )
}
