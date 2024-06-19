import { useEffect } from "react"
import Posts from "../../components/posts/Posts"
import Share from "../../components/share/Share"
import "./home.css"
import { useSelector } from "react-redux"

const Home = () => {
  const name = useSelector((state) => state.user.user.name);
  useEffect(()=>{
console.log(name)
  },[])
  return (
    <div className="home">
      <Share/>
      <Posts/>
    </div>
  )
}

export default Home