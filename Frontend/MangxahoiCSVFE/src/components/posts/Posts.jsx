import { useEffect, useState, useCallback } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { useSearchParams } from "react-router-dom";
import APIs, { authApi, endpoints } from "../../configs/APIs";
import Post from "../post/Post";
import MySprinner from "../mySprinner/MySprinner";
import "./posts.css";

const Posts = () => {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [pageNumber, setPageNumber] = useState(1);
    const [hasNext, setHasNext] = useState(true);
    const [q] = useSearchParams();

    const loadPosts = useCallback(async () => {
        setLoading(true);
        try {
            let url = `${endpoints['posts']}?isPostUser=true&pageNumber=${pageNumber}`;

            let isPostUser = q.get('isPostUser');
            if (isPostUser) {
                url = `${endpoints['posts']}?isPostUser=${isPostUser}&pageNumber=${pageNumber}`;
            }

            console.info(url);
            const res = await authApi().get(url);

            if (pageNumber === 1) {
                setPosts(res.data.posts);
            } else {
                setPosts(current => [...current, ...res.data.posts]);
            }
            setHasNext(res.data.next);
        } catch (error) {
            console.error("Failed to fetch posts", error);
        } finally {
            setLoading(false);
        }
    }, [pageNumber, q]);

    useEffect(() => {
        loadPosts();
    }, [loadPosts]);

    const handleScroll = useCallback(() => {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 2 && hasNext && !loading) {
            setPageNumber(prev => prev + 1);
        }
    }, [hasNext, loading]);

    useEffect(() => {
        window.addEventListener('scroll', handleScroll);
        return () => window.removeEventListener('scroll', handleScroll);
    }, [handleScroll]);

    return (
        <Container>
            <Row>
                {posts.map(post => (
                    <Col md={6} key={post.postID} className='p-2'>
                        <Post post={post} />
                    </Col>
                ))}
            </Row>
            {loading && <MySprinner />}
        </Container>
    );
}

export default Posts;
