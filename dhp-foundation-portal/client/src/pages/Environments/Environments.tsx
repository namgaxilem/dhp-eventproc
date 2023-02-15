import { Button } from "antd";
import { useEffect } from "react";
import { useDispatch } from 'react-redux';
import { useNavigate } from "react-router-dom";
import { changeTitle } from "../../store/title/titleSlice";

function Environments() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(changeTitle("Environments"));
  });

  return <>
    <Button type="primary" onClick={() => navigate('/environments/initializer')}>
      Deploy
    </Button>
    <hr />
    <Button type="ghost" onClick={() => navigate('/environments/654-asd-we-UUID')}>
      Environments detail
    </Button>
  </>
}

export default Environments;